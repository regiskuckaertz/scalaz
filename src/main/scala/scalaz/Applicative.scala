package scalaz

abstract class Applicative[F[_]](using ev: Apply[F]):
  export ev._

  def point[A](a: => A): F[A]

object Applicative:
  inline def apply[F[_]](using F: Applicative[F]): Applicative[F] = F

  given ListApplicative: Applicative[List] with
    def point[A](a: => A): List[A] =
      List(a)