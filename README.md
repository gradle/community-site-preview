# Previews for Open Source Gradle.org sub-sites

GitHub Pages site for previewing community.gradle.org and other open source resources.
When submitting pull requests to the sites, we can show previews of the built sites.

> **NOTE:** This repository has no its own content except readme and a few workflows,
> and there may be periodic force pushes to reset the previews history

## Sites

This repo serves sites that are open source:

* https://community.gradle.org/
* https://cookbook.gradle.org/
* https://declarative.gradle.org/

More sites may be added in the future.

## License

All content is licensed under the original licenses of the included repositories.

## Contributing

This is an infrastructure repository maintained by Gradle Inc.
There is no community content to contribute to,
but you are welcome to contribute to all the listed sites!

The sites listed above will push previews to the `gh-pages` branch using their pipelines.
To do so, they use deploy keys (SSH), the public key is [here](./id_rsa.pub).
The private key is stored in the company-internal password manager.
