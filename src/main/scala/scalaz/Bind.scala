package scalaz

abstract class Bind[F[_]](using ev: Apply[F]):
  export ev._

  def bind[A, B](f: A => F[B]): F[A] => F[B] = fa => join(map(f)(fa))

  def join[A]: F[F[A]] => F[A] = bind(identity)

object Bind:
  inline def apply[F[_]](using F: Bind[F]): Bind[F] = F

  given ListBind: Bind[List] with
    override def bind[A, B](f: A => List[B]): List[A] => List[B] = _.flatMap(f)