package scalaz

abstract class Semigroup[A]:
  def append(x: => A, y: => A): A

  extension (x: A) def <> (y: A): A = append(x, y)

object Semigroup:
  inline def apply[A](using S: Semigroup[A]): Semigroup[A] = S

  inline def make[A](f: (=> A, => A) => A): Semigroup[A] =
    new Semigroup[A]:
      def append(x: => A, y: => A): A = f(x, y)