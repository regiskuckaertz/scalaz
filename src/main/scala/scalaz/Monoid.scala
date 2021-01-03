package scalaz

abstract class Monoid[A](using ev: Semigroup[A]):
  export ev._

  def zero: A

object Monoid:
  inline def apply[A: Semigroup](using M: Monoid[A]): Monoid[A] = M

  inline def make[A: Semigroup](x: => A): Monoid[A] =
    new Monoid[A]:
      def zero: A = x
