fun iterate(array: IntArray,f: (Int) -> Unit) {
  array.forEach{ f(it) }
}

fun fizzbuzzInterate() {  
  val fizz : (Int) -> String? = { if (it % 3 == 0) "fizz" else null}  
  val buzz : (Int) -> String? = { if (it % 5 == 0) "buzz" else null}  
  val fizzbuzz : (Int) -> String = { 
    operatorPlus(fizz(it), buzz(it)) ?: it.toString() 
  }  
  val output : (String?) -> Unit = { 
    println(it ?: "") 
  }  
  iterate(IntArray(100){it}, { output(fizzbuzz(it)) })
}