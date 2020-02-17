object Classes extends App {
  val pt = new Point(1, 2)
  println(pt)
  pt.move(10, 10)
  println(pt)

  val circ = new Circle(pt,5)
  println(circ)
  println(circ.area)
  println(circ.radius)
  circ.radius = 10
  println(circ)

}