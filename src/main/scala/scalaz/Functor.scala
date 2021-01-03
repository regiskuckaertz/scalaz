package scalaz

abstract class Functor[F[_]]:
  def map[A, B](f: A => B): F[A] => F[B]

  def xmap[A, B](fa: F[A], f: A => B, g: B => A): F[B] =
    map(f)(fa)

  /** Inject `a` to the left of `B`s in `f`. */
  def strengthL[A, B](a: A, f: F[B]): F[(A, B)] = map((b: B) => (a, b))(f)

  /** Inject `b` to the right of `A`s in `f`. */
  def strengthR[A, B](f: F[A], b: B): F[(A, B)] = map((a: A) => (a, b))(f)

  /** Lift `apply(a)`, and apply the result to `f`. */
  def mapply[A, B](a: A)(f: F[A => B]): F[B] = map((ff: A => B) => ff(a))(f)

  /** Twin all `A`s in `fa`. */
  def fpair[A](fa: F[A]): F[(A, A)] = map((a: A) => (a, a))(fa)

  /** Pair all `A`s in `fa` with the result of function application. */
  def fproduct[A, B](fa: F[A])(f: A => B): F[(A, B)] = map((a: A) => (a, f(a)))(fa)

  /**
   * Empty `fa` of meaningful pure values, preserving its
   * structure.
   */
  def void[A]: F[A] => F[Unit] = map(_ => ())

  def counzip[A, B](a: F[A] Either F[B]): F[A Either B] =
    a.fold(map(Left(_)), map(Right(_)))

  /**The composition of Functors `F` and `G`, `[x]F[G[x]]`, is a Functor */
  def compose[G[_]](using G0: Functor[G]): Functor[F ∘ G] =
    Functor.make[F ∘ G](
      [A, B] => (f: A => B) => map(G0.map(f))
    )

  /**The product of Functors `F` and `G`, `[x](F[x], G[x]])`, is a Functor */
  def product[G[_]](implicit G0: Functor[G]): Functor[F * G] =
    Functor.make[F * G](
      [A, B] => (f: A => B) => (fga: (F[A], G[A])) => (map(f)(fga._1), G0.map(f)(fga._2))
    )

  extension [A, B](fa: F[A]) def apply(f: A => B): F[B] = map(f)(fa)

object Functor:
  inline def apply[F[_]](using F: Functor[F]): Functor[F] = F

  inline def make[F[_]](g: [A, B] => (A => B) => F[A] => F[B]): Functor[F] =
    new Functor[F]:
      def map[A, B](f: A => B): F[A] => F[B] = g(f)

  given ListFunctor: Functor[List] with
    def map[A, B](f: A => B): List[A] => List[B] =
      _.map(f)
