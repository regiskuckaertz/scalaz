package scalaz

type *[F[_], G[_]] = [A] =>> (F[A], G[A])
type +[F[_], G[_]] = [A] =>> F[A] Either G[A]
type ∘[F[_], G[_]] = [A] =>> F[G[A]]

def zero[A](using M: Monoid[A]): A = M.zero

// does not compile:
// def point[F[_]](using M: Monad[F]): [A] => (=> A) => F[A] =
//   [A] => (x: => A) => M.point(x)