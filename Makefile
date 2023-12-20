run:
	@./mvnw compile quarkus:dev

add:
	@./mvnw quarkus:add-extension -Dextensions="${name}"