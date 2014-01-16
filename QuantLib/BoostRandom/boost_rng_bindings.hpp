//============================================================================
// Name        : boost_rng_bindings.hpp
// Author      : Mikail Sheikh
// Date        : 28/11/2013
// Copyright   : Distributed under the MIT License (MIT)
// Description : A QuantLib wrapper for Boost random number generators
//============================================================================

#ifndef BOOSTRNG_HPP_
#define BOOSTRNG_HPP_

#include <ql/types.hpp>
#include <ql/math/randomnumbers/seedgenerator.hpp>
#include <ql/math/randomnumbers/rngtraits.hpp>
#include <ql/methods/montecarlo/sample.hpp>

#include <boost/static_assert.hpp>
#include <boost/random.hpp>

using namespace QuantLib;




template<class RNG>
class GenericBoostIntRNG
{
  private:
    mutable RNG generator;
    static Real ceil_value;
  public:
    typedef Sample<Real> sample_type;
    typedef typename RNG::result_type result_type;

    BOOST_STATIC_ASSERT (std::numeric_limits<result_type>::is_integer);

    GenericBoostIntRNG(result_type seed = 0);

    //! return a random number in the (0.0, 1.0)-interval
    //! For random number generators with a precision larger than
    Real nextReal() const
    {
      return (Real(generator()) + 0.5) / ceil_value;
    }

    /*! returns a sample with weight 1.0 containing a random number
     in the (0.0, 1.0) interval  */
    sample_type next() const
    {
      return sample_type(nextReal(), 1.0);
    }

  private:
    result_type getSeed(result_type seed);
};

template<class RNG>
class GenericBoostFloatRNG
{
  private:
    mutable RNG generator;
    static Real ceil_value;
  public:
    typedef Sample<Real> sample_type;

    typedef typename RNG::result_type result_type;
    typedef boost::uint32_t seed_type;
    BOOST_STATIC_ASSERT (std::numeric_limits<result_type>::is_float);

    GenericBoostFloatRNG(seed_type seed = 0);

    /*! returns a sample with weight 1.0 containing a random number
     in the (0.0, 1.0) interval  */
    sample_type next() const
    {
      return sample_type(nextReal(), 1.0);
    }
    //! return a random number in the (0.0, 1.0)-interval
    result_type nextFloat() const
    {
      return generator();
    }

  private:
    seed_type getSeed(seed_type seed);
};

typedef GenericBoostIntRNG<boost::mt11213b> BoostMT11213bUniformRng;
typedef GenericPseudoRandom<
    BoostMT11213bUniformRng,
    InverseCumulativeNormal> PseudoRandomBoostMT11213b;

typedef GenericBoostIntRNG<boost::mt19937> BoostMT19937UniformRng;
typedef GenericPseudoRandom<
    BoostMT19937UniformRng,
    InverseCumulativeNormal> PseudoRandomBoostMT19937;


template<class RNG>
Real GenericBoostIntRNG<RNG>::ceil_value = Real(RNG::max()) + 1.0;

template<class RNG>
GenericBoostIntRNG<RNG>::GenericBoostIntRNG(result_type seed) :
    generator(GenericBoostIntRNG<RNG>::getSeed(seed))
{
}

template<class RNG>
typename GenericBoostIntRNG<RNG>::result_type GenericBoostIntRNG<RNG>::getSeed(
    result_type seed)
{
  unsigned long s = (seed != 0 ? seed : SeedGenerator::instance().get());
  return (result_type) (s % RNG::max());
}


template<class RNG>
GenericBoostFloatRNG<RNG>::GenericBoostFloatRNG(result_type seed) :
    generator(GenericBoostFloatRNG<RNG>::getSeed(seed))
{
}

template<class RNG>
typename GenericBoostFloatRNG<RNG>::seed_type GenericBoostFloatRNG<RNG>::getSeed(
    seed_type seed)
{
  unsigned long s = (seed != 0 ? seed : SeedGenerator::instance().get());
  return (seed_type) (s % 0xffffffff);
}

#endif /* BOOSTRNG_HPP_ */
