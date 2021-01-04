package scalaz

abstract class Monad[F[_]](using ev1: Applicative[F], ev2: Bind[F]):
  export ev1._
  export ev2.{ bind, join, >>= }

object Monad:
  inline def apply[F[_]](using F: Monad[F]): Monad[F] = F

  given ListMonad: Monad[List] with {}