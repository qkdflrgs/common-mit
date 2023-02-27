function fizzbuzzIterator() {
  function iterate(array, transform) {
    array.forEach(transform);
  }
  let fizz = (index) => {
    return index % 3 === 0 ? 'fizz' : undefined;
  };
  let buzz = (index) => {
    return index % 5 === 0 ? 'buzz' : undefined;
  };
  let fizzbuzz = (index) => {
    let combine = fizbuz(fizz(index), buzz(index));
    if (combine === undefined) return `${index}`;
    return combine;
  };
  let output = (result) => {
    console.log(result);
  };

  iterate(
    Array(100)
      .fill()
      .map((x, i) => i + 1),
    (index) => {
      output(fizzbuzz(index));
    }
  );
}
