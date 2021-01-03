package scalaz

abstract class Traverse[F[_]](using ev1: Functor[F], ev2: Foldable[F]):
  export ev1._
  export ev2._
  
  def traverseImpl[G[_]: Applicative, A, B](fa: F[A])(f: A => G[B]): G[F[B]]

object Traverse:
  inline def apply[F[_]](using F: Traverse[F]): Traverse[F] = F

  given ListTraverse: Traverse[List] with
    def traverseImpl[F[_], A, B](l: List[A])(f: A => F[B])(using F: Applicative[F]) = ???
