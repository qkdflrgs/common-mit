func fizzbuzzInterate() {
    func iterate<A>(_ arr: [A], _ f: ((A) -> ())) {
        arr.forEach({ f($0) })
    }
    let fizz = { $0 % 3 == 0 ? "fizz" : nil}
    let buzz = { $0 % 5 == 0 ? "buzz" : nil}
    let fizzbuzz = { index in fizz(index) + buzz(index) ?? String(index) }
    let output = { print($0 ?? "") }

    iterate(Array(1...100)) { index in output(fizzbuzz(index)) }
}