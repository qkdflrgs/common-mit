const fib = (n) => {
  if (n === 1) return 1;

  let curr = 0;
  let prev = 1;
  let prevPrev = 0;

  for (let i = 2; i <= n; i++) {
    curr = prev + prevPrev;
    prevPrev = prev;
    prev = curr;
  }

  return curr;
};
