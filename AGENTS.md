# Repository Guidelines

## Project Structure & Module Organization

This is a Maven multi-module RuoYi/Yudao backend. The root `pom.xml` currently builds `yudao-dependencies`, `yudao-framework`, `yudao-server`, `yudao-module-system`, and `yudao-module-infra`; other business modules are present but commented out for faster builds. Shared starters live in `yudao-framework/*`, the Spring Boot entrypoint is `yudao-server/src/main/java/cn/iocoder/yudao/server/YudaoServerApplication.java`, and module code follows `controller`, `service`, `dal`, `convert`, and `enums` packages. Tests live under each module’s `src/test/java`. SQL initialization files are in `sql/<database>/`. Frontend snapshots are under `yudao-ui/*`; only run frontend tooling in a UI subproject that contains its own `package.json`.

## Build, Test, and Development Commands

- `mvn clean install -DskipTests`: build all enabled backend modules.
- `mvn test`: run JUnit 5 tests for enabled modules.
- `mvn -pl yudao-module-infra -am test`: run one module and its required dependencies.
- `mvn -pl yudao-server -am spring-boot:run -Dspring-boot.run.profiles=local`: start the API with `application-local.yaml`.
- `mvn -pl yudao-server -am package -DskipTests`: create the server jar.

When enabling an optional module, uncomment both its root `<module>` entry and the matching `yudao-server` dependency.

## Coding Style & Naming Conventions

Use Java 8-compatible code, UTF-8, four-space indentation, Lombok, MapStruct, and Spring annotations consistent with nearby classes. Keep package names under `cn.iocoder.yudao`. Follow existing suffixes: `*Controller`, `*Service`, `*ServiceImpl`, `*Mapper`, `*DO`, `*ReqVO`, `*RespVO`, and `*Convert`. REST controllers return `CommonResult` and usually use `success(...)`. Prefer existing framework helpers over new utility abstractions.

## Testing Guidelines

Backend tests use JUnit 5 and the project’s starter test utilities, commonly extending classes such as `BaseDbUnitTest`. Name tests `ClassNameTest` or `ClassNameTests`, with methods like `testCreateX_success` and `testCreateX_fieldInvalid`. Put fixtures in `src/test/resources`. Run targeted module tests before full `mvn test` when changing only one module.

## Commit & Pull Request Guidelines

Recent history mixes release notes, sync commits, merges, and scoped messages such as `feat(mes)：优化 JDK8 的兼容性`. Prefer concise scoped commits: `feat(module): ...`, `fix(module): ...`, or the existing Chinese full-width style when matching upstream. PRs should describe the changed module, list verification commands, call out database/config changes, link issues when available, and include screenshots for UI changes.

## Security & Configuration Tips

Do not commit local secrets or environment-specific credentials. Keep profile-specific settings in `yudao-server/src/main/resources/application-local.yaml` or `application-dev.yaml`, and document required Redis, database, MQ, or third-party service setup in the PR.
