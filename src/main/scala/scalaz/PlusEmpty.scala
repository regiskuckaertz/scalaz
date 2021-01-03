package scalaz

abstract class PlusEmpty[F[_]](using ev: Plus[F]):
  export ev.plus
  
  def empty[A]: F[A]

  given monoid[A]: Monoid[F[A]](using ev.semigroup) with
    def zero: F[A] = empty[A]

object PlusEmpty:
  inline def apply[F[_]](using F: PlusEmpty[F]): PlusEmpty[F] = F
