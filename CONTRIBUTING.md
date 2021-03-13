# How to Contribute

Hey, it's nice to see fellow software developers interested in contributing to a project that aims to assist OSRS players in living a healthy lifestyle.

Before contributing to a bugfix or feature, please search [Issues](https://github.com/jmakhack/hydrate-reminder/issues) to see if the request already exists and/or is currently being worked on. If not, create a [New Issue](https://github.com/jmakhack/hydrate-reminder/issues/new/choose) describing the enhancement or bugfix for the plugin. No pull requests should be created without an associated issue.


Make sure to ensure that no newly added functionality goes against [Jagex's rules](https://secure.runescape.com/m=news/another-message-about-unofficial-clients?oldschool=1).  
If adding any third party dependencies, follow the guide found at [plugin-hub](https://github.com/runelite/plugin-hub#third-party-dependencies).

## Testing

They hydrate-reminder project contains jUnit tests located in [src/test/java/com/hydratereminder](https://github.com/jmakhack/hydrate-reminder/tree/master/src/test/java/com/hydratereminder). Please ensure that all the unit tests are passing before submitting a change. If the unit tests are failing as a result of a code change, identify why the test is failing and either fix the newly written code or update the test if it is no longer valid. Adding new unit tests for new features/enhancements is strongly encouraged but not strictly required (a separate testing task will be opened if not needed).

When a new pull request is opened, the unit tests are run automatically as part of the pre-merge pipeline for every update to ensure that they are all passing before merging in the code. New code changes are not allowed to be merged into the master branch until all tests are passing. The unit tests are also run on a nightly basis to ensure the project's stable condition. The current build status can be viewed on the [README](https://github.com/jmakhack/hydrate-reminder/blob/master/README.md) as a badge. Manual testing is also done as a final step before every release as a sanity check of the plugin's quality.

## Submitting Changes

Please send a [GitHub Pull Request](https://github.com/jmakhack/hydrate-reminder/pull/new/master) with clear details on what was done (read more about [pull requests](http://help.github.com/pull-requests/)). All pull requests should be targeted towards the master branch.

Always write a clear and concise commit message for your commits. One-line messages are fine for small changes, but bigger changes should look like the following:

    $ git commit -m "brief commit summary
    > 
    > A paragraph describing what changed and its impact."

## Coding Conventions
When adding new classes, methods, and fields, please add the appropriate JavaDocs to keep the codebase in an easily maintainable state.

Otherwise, there are no formal, documented coding conventions for this project to follow, but please take a look at the existing code under [src/main/java/com/hydratereminder](https://github.com/jmakhack/hydrate-reminder/tree/master/src/main/java/com/hydratereminder) and follow the existing conventions.
