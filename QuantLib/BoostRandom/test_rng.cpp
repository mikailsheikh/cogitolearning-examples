//============================================================================
// Name        : test_rng.cpp
// Author      : Mikail Sheikh
// Date        : 28/11/2013
// Copyright   : Distributed under the MIT License (MIT)
// Description : A QuantLib wrapper for Boost random number generators
//============================================================================

#include "boost_rng_bindings.hpp"

#include <ql/time/calendar.hpp>
#include <ql/time/calendars/target.hpp>
#include <ql/time/daycounter.hpp>
#include <ql/time/daycounters/actual365fixed.hpp>
#include <ql/option.hpp>
#include <ql/exercise.hpp>
#include <ql/quote.hpp>
#include <ql/quotes/simplequote.hpp>
#include <ql/termstructures/yieldtermstructure.hpp>
#include <ql/termstructures/yield/flatforward.hpp>
#include <ql/termstructures/volatility/equityfx/blackconstantvol.hpp>
#include <ql/termstructures/volatility/equityfx/blackvoltermstructure.hpp>
#include <ql/instruments/payoffs.hpp>
#include <ql/instruments/vanillaoption.hpp>
#include <ql/pricingengines/vanilla/mceuropeanengine.hpp>
#include <ql/processes/blackscholesprocess.hpp>
#include <ql/settings.hpp>
#include <ql/handle.hpp>
#include <ql/utilities/dataformatters.hpp>

#include <boost/timer/timer.hpp>
#include <boost/shared_ptr.hpp>

#include <iostream>
#include <iomanip>
#include <ctime>

int main()
{

  try
  {
    // set up dates
    Calendar calendar = TARGET();
    Date todaysDate(15, May, 1998);
    Date settlementDate(17, May, 1998);
    Settings::instance().evaluationDate() = todaysDate;

    // our options
    Option::Type type(Option::Put);
    Real underlying = 36;
    Real strike = 40;
    Spread dividendYield = 0.00;
    Rate riskFreeRate = 0.06;
    Volatility volatility = 0.20;
    Date maturity(17, May, 1999);
    DayCounter dayCounter = Actual365Fixed();

    std::cout << "Option type = " << type << std::endl;
    std::cout << "Maturity = " << maturity << std::endl;
    std::cout << "Underlying price = " << underlying << std::endl;
    std::cout << "Strike = " << strike << std::endl;
    std::cout << "Risk-free interest rate = " << io::rate(riskFreeRate)
        << std::endl;
    std::cout << "Dividend yield = " << io::rate(dividendYield) << std::endl;
    std::cout << "Volatility = " << io::volatility(volatility) << std::endl;
    std::cout << std::endl;
    std::string method;
    std::cout << std::endl;

    // write column headings
    Size widths[] =
      { 35, 14 };
    std::cout << std::setw(widths[0]) << std::left << "Method"
        << std::setw(widths[1]) << std::left << "European" << std::endl;

    boost::shared_ptr<Exercise> europeanExercise(
        new EuropeanExercise(maturity));

    Handle<Quote> underlyingH(
        boost::shared_ptr<Quote>(new SimpleQuote(underlying)));

    // bootstrap the yield/dividend/vol curves
    Handle<YieldTermStructure> flatTermStructure(
        boost::shared_ptr<YieldTermStructure>(
            new FlatForward(settlementDate, riskFreeRate, dayCounter)));
    Handle<YieldTermStructure> flatDividendTS(
        boost::shared_ptr<YieldTermStructure>(
            new FlatForward(settlementDate, dividendYield, dayCounter)));
    Handle<BlackVolTermStructure> flatVolTS(
        boost::shared_ptr<BlackVolTermStructure>(
            new BlackConstantVol(settlementDate, calendar, volatility,
                dayCounter)));
    boost::shared_ptr<StrikedTypePayoff> payoff(
        new PlainVanillaPayoff(type, strike));
    boost::shared_ptr<BlackScholesMertonProcess> bsmProcess(
        new BlackScholesMertonProcess(underlyingH, flatDividendTS,
            flatTermStructure, flatVolTS));

    // options
    VanillaOption europeanOption(payoff, europeanExercise);

    Real npv;
    Size timeSteps;
    Size mcSeed = 42;

    // Monte Carlo Method: MC (crude)
    timeSteps = 1;
    method = "MC (crude)";
    {
      boost::timer::auto_cpu_timer timer;
      boost::shared_ptr<PricingEngine> mcengine1;
      mcengine1 = MakeMCEuropeanEngine<PseudoRandom>(bsmProcess).withSteps(
          timeSteps).withAbsoluteTolerance(0.002).withSeed(mcSeed);
      europeanOption.setPricingEngine(mcengine1);
      npv = europeanOption.NPV();
    }
    // Real errorEstimate = europeanOption.errorEstimate();
    std::cout << std::setw(widths[0]) << std::left << method << std::fixed
        << std::setw(widths[1]) << std::left << npv << std::endl;

    timeSteps = 1;
    method = "MC (crude MT11213b)";
    {
      boost::timer::auto_cpu_timer timer;
      boost::shared_ptr<PricingEngine> mcengine2;
      mcengine2 =
          MakeMCEuropeanEngine<PseudoRandomBoostMT11213b>(bsmProcess).withSteps(
              timeSteps).withAbsoluteTolerance(0.002).withSeed(mcSeed);
      europeanOption.setPricingEngine(mcengine2);
      npv = europeanOption.NPV();
    }
    // Real errorEstimate = europeanOption.errorEstimate();
    std::cout << std::setw(widths[0]) << std::left << method << std::fixed
        << std::setw(widths[1]) << std::left << npv << std::endl;

    timeSteps = 1;
    method = "MC (crude MT19937)";
    {
      boost::timer::auto_cpu_timer timer;
      boost::shared_ptr<PricingEngine> mcengine3;
      mcengine3 =
          MakeMCEuropeanEngine<PseudoRandomBoostMT19937>(bsmProcess).withSteps(
              timeSteps).withAbsoluteTolerance(0.002).withSeed(mcSeed);
      europeanOption.setPricingEngine(mcengine3);
      npv = europeanOption.NPV();
    }

    std::cout << std::setw(widths[0]) << std::left << method << std::fixed
        << std::setw(widths[1]) << std::left << npv << std::endl;
  }
  catch (std::exception& e)
  {
    std::cerr << e.what() << std::endl;
    return 1;
  }
  catch (...)
  {
    std::cerr << "unknown error" << std::endl;
    return 1;
  }
}
