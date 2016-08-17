git_version = $$(git branch 2>/dev/null | sed -e '/^[^*]/d'-e's/* \(.*\)/\1/')
npm_bin= $$(npm bin)

all: test
test: server
	macaca server --verbose
	mvn -s settings.xml clean install
	mvn test
server:
	@${npm_bin}/macaca server --verbose &
.PHONY: test
