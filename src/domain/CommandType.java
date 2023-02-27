package domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import service.SHA256Service;
import service.ZlibService;

public enum CommandType {
	LIST {
		@Override
		public List<Map<String, Object>> doTask(String directoryPath) {
			return taskTemplate(directoryPath, file -> {
				try {
					return Files.size(file.toPath());
				} catch (IOException e) {
					throw new IllegalArgumentException("[ERROR] 잘못된 경로를 입력했습니다.");
				}
			});
		}
	}, HASH {
		@Override
		public List<Map<String, Object>> doTask(String directoryPath) {
			return taskTemplate(directoryPath, SHA256Service::encrypt);
		}
	}, ZLIB {
		@Override
		public List<Map<String, Object>> doTask(String directoryPath) {
			return taskTemplate(directoryPath, ZlibService::compress);
		}
	};

	public abstract List<Map<String, Object>> doTask(String directoryPath);

	List<Map<String, Object>> taskTemplate(String directoryPath, Function<File, Object> logic) {
		List<Map<String, Object>> ret = new ArrayList<>();

		File[] files = new File(directoryPath).listFiles();

		for (File file : Objects.requireNonNull(files)) {

			if (file.isFile()) {
				ret.add(Map.of(file.getName(), logic.apply(file)));
			}
			if (file.isDirectory()) {
				ret.addAll(doTask(file.getPath()));
			}
		}
		return ret;
	}

	public static CommandType of(String command) {
		return switch (command) {
			case "list" -> LIST;
			case "hash" -> HASH;
			case "zlib" -> ZLIB;
			default -> throw new IllegalArgumentException("[ERROR] 명령어는 [list, hash, zlib] 중 하나여야 합니다.");
		};
	}
}
