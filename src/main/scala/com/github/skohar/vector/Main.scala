package com.github.skohar.vector

import shapeless.HList._
import shapeless._

trait Semigroup[S] {
  def append(s1: S, s2: S): S
}

case class Vector3D(x: Int, y: Int, z: Int)

object Main {

  def plus[A](a: A, b: A)(implicit semigroup: Semigroup[A]) = semigroup.append(a, b)

  def to(v: Vector3D): Int :: Int :: Int :: HNil = v.x :: v.y :: v.z :: HNil

  def from(hlist: Int :: Int :: Int :: HNil): Vector3D = Vector3D(hlist.head, hlist.tail.head, hlist.tail.tail.head)

  implicit val intInstance = new Semigroup[Int] {
    def append(x: Int, y: Int) = x + y
  }

  implicit val nilInstance = new Semigroup[HNil] {
    def append(x: HNil, y: HNil) = HNil
  }

  implicit def consInstance[H, T <: HList](implicit H: Semigroup[H], T: Semigroup[T]) = new Semigroup[H :: T] {
    def append(x: H :: T, y: H :: T) = H.append(x.head, y.head) :: T.append(x.tail, y.tail)
  }

  def subst[A, B](to: A => B, from: B => A)(implicit instance: Semigroup[B]) = new Semigroup[A] {
    def append(a1: A, a2: A) = from(instance.append(to(a1), to(a2)))
  }

  implicit val vectorInstance: Semigroup[Vector3D] = subst(to, from)

  def main(args: Array[String]) {
    println(plus(1 :: 3 :: HNil, 2 :: 4 :: HNil))
    println(plus(Vector3D(1, 2, 3), Vector3D(1, 2, 3)))
  }
}
