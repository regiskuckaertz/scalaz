package scalaz

abstract class Foldable[F[_]]:
  def foldMap[A, B: Monoid](f: A => B): F[A] => B

  def foldRight[A, B](z: => B)(f: (A, => B) => B): F[A] => B

  def foldLeft[A, B](z: B)(f: (B, A) => B): F[A] => B

object Foldable:
  inline def apply[F[_]](using F: Foldable[F]): Foldable[F] = F

  given ListFoldable: Foldable[List] with
    def foldMap[A, B: Monoid](f: A => B): List[A] => B = 
      _.foldLeft(zero)((b, a) => b <> f(a))

    def foldRight[A, B](z: => B)(f: (A, => B) => B): List[A] => B =
      _.foldRight(z)(f(_, _))

    def foldLeft[A, B](z: B)(f: (B, A) => B): List[A] => B =
      _.foldLeft(z)(f)
