struct Video {
    var id: String
    var title: String
    var time: Int
    var next:String?

    init(title:String, time:Int, next:String?=nil) {
        let id:String = UUID().uuidString
        self.id = String(id[...id.index(id.startIndex, offsetBy: 7)])
        self.title = title
        self.time = time
        self.next = next
    }


    private fun printVideo {
      print("hello")
      print("df")
      print("q")
      print("df")
      print("daf")
      print("hel1231lo")
      print("34513")
      print("hello")
      print("df")
      print("q")
      print("df")
      print("daf")
      print("hel1231lo")
      print("34513")
       print("hello")
       print("df")
       print("q")
       print("df")
       print("daf")
       print("hel1231lo")
       print("34513")
       print("hello")
       print("df")
       print("q")
       print("df")
       print("daf")
       print("hel1231lo")
       print("34513")
       print("hello")
       print("df")
       print("q")
       print("df")
       print("daf")
       print("hel1231lo")
       print("34513")
       print("hello")
       print("df")
       print("q")
       print("df")
       print("daf")
       print("hel1231lo")
       print("34513")
       print("hello")
       print("df")
       print("q")
       print("df")
       print("daf")
       print("hel1231lo")
       print("34513")
       print("hello")
       print("df")
       print("q")
       print("df")
       print("daf")
       print("hel1231lo")
       print("34513")
    }
}