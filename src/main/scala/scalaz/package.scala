package scalaz

type *[F[_], G[_]] = [A] =>> (F[A], G[A])
type +[F[_], G[_]] = [A] =>> F[A] Either G[A]
type ∘[F[_], G[_]] = [A] =>> F[G[A]]

def zero[A](using M: Monoid[A]): A = M.zero
