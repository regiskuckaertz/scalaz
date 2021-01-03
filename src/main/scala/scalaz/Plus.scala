package scalaz

abstract class Plus[F[_]]:
  def plus[A](a: F[A], b: => F[A]): F[A]

  given semigroup[A]: Semigroup[F[A]] with
    def append(a: => F[A], b: => F[A]): F[A] = plus(a, b)

object Plus:
  inline def apply[F[_]](using F: Plus[F]): Plus[F] = F

  