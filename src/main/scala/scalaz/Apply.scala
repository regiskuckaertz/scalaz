package scalaz

abstract class Apply[F[_]](using ev: Functor[F]):
  export ev._
  
  def ap[A,B](fa: => F[A])(f: => F[A => B]): F[B]

  def ap2[A,B,C](fa: => F[A], fb: => F[B])(f: F[(A,B) => C]): F[C] =
    ap(fb)(ap(fa)(f.apply(_.curried)))

  def apply2[A, B, C](fa: => F[A], fb: => F[B])(f: (A, B) => C): F[C] =
    ap(fb)(map(f.curried)(fa))

object Apply:
  inline def apply[F[_]](using F: Apply[F]): Apply[F] = F

  given ListApply: Apply[List] with
    def ap[A, B](fa: => List[A])(f: => List[A => B]): List[B] = ???
