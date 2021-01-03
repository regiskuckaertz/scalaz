package scalaz

abstract class ApplicativePlus[F[_]](using ev1: Applicative[F], ev2: PlusEmpty[F]):
  export ev1._
  export ev2._

object ApplicativePlus:
  inline def apply[F[_]](using F: ApplicativePlus[F]): ApplicativePlus[F] = F
