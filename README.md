# lab-dep-multimodule-maven
My Lab Dep Multimodule On Maven

## Branch strategy

| Branch | Purpose |
|--------|---------|
| `main` | Stable, production-ready releases |
| `develop` | Integration branch — all feature branches merge here before going to `main` |
| `feature/*` | Short-lived branches for new features; opened as PRs targeting `develop` |

## Module structure

| Module | Published to Maven repo? | Description |
|--------|--------------------------|-------------|
| `data`  | ✅ Yes | In-memory `UserRepository` (Map-backed CRUD) + `User` POJO |
| `auth`  | ✅ Yes | `AuthService` (register / authenticate) + `Credentials` POJO |
| `log`   | ✅ Yes | `JsonLogFormatter` (converts `LogEntry` → JSON) + `LogEntry` POJO |
| `coverage` | ❌ No (build-only) | Aggregates JaCoCo reports — never deployed to any Maven repository |

The `coverage` module is a **build-only** utility. It is excluded from `mvn deploy` and `mvn install`
via `<maven.deploy.skip>true</maven.deploy.skip>` and `<maven.install.skip>true</maven.install.skip>`
in its `pom.xml`, so consumers who declare a dependency on `data`, `auth`, or `log` will never see
or need the `coverage` artifact.

## JaCoCo aggregate coverage

Run `mvn verify` from the project root. The consolidated HTML/XML/CSV report is written to:

```
coverage/target/site/jacoco-aggregate/
```
