class Circle (circle: Point, var radius: Int) {
  def area() : Double = {
    math.Pi * radius * radius
  }

  override def toString: String =
    "(" + circle.x + ", " + circle.y + "), radius = " + radius
}
