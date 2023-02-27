public static void fizzbuzzIterate() {
	BiConsumer<Stream<String>, Consumer<String>> iterate = (list, f) -> {
		list.forEach(f);
	};
	Function<Integer, Optional<String>> fizz = (Integer index) -> {
		return index % 3 == 0 ? Optional.of("fizz") : null;
	};
	Function<Integer, Optional<String>> buzz = (Integer index) -> {
		return index % 5 == 0 ? Optional.of("buzz") : null;
	};
	Function<Integer, String> fizzbuzz = (Integer index) -> {
		Optional<String> result = operatorPlus(fizz.apply(index), buzz.apply(index));
		return result == null ? index.toString() : result.get();
	};
	Consumer<String> output = result -> {
		System.out.println(result);
	};
	Stream<String> stream = IntStream.range(1,100).mapToObj(index -> fizzbuzz.apply(index));
	iterate.accept(stream, output);
}