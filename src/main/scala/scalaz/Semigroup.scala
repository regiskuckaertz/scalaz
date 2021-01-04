package scalaz

abstract class Semigroup[A]:
  def append(x: => A, y: => A): A

  extension (x: A) def <> (y: A): A = append(x, y)

object Semigroup:
  inline def apply[A](using S: Semigroup[A]): Semigroup[A] = S
