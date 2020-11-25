# How to Contribute

Hey, it's nice to see fellow software developers interested in contributing to a project that aims to assist OSRS players in living a healthy lifestyle.

Before contributing to a bugfix or feature, please search [Issues](https://github.com/jmakhack/hydrate-reminder/issues) to see if the request already exists and/or is currently being worked on. If not, create a [New Issue](https://github.com/jmakhack/hydrate-reminder/issues/new/choose) describing the enhancement or bugfix for the plugin. No pull requests should be created without an associated issue.


Make sure to ensure that no newly added functionality goes against [Jagex's rules](https://secure.runescape.com/m=news/another-message-about-unofficial-clients?oldschool=1).  
If adding any third party dependencies, follow the guide found at [plugin-hub](https://github.com/runelite/plugin-hub#third-party-dependencies).

## Testing

As of 11/25/2020 (Hydrate Reminder v1.0.1), there are no automated tests setup for this project. Until automated tests are setup, make sure to test the build thoroughly before submitting a pull request. Each pull request build will also be tested thoroughly by the reviewer.

## Submitting Changes

Please send a [GitHub Pull Request](https://github.com/jmakhack/hydrate-reminder/pull/new/master) with clear details on what was done (read more about [pull requests](http://help.github.com/pull-requests/)). All pull requests should be targeted towards the master branch.

Always write a clear and concise commit message for your commits. One-line messages are fine for small changes, but bigger changes should look like the following:

    $ git commit -m "brief commit summary
    > 
    > A paragraph describing what changed and its impact."

## Coding Conventions

There are no formal, documented coding conventions for this project to follow, but please take a look at the existing code under [src/main/java/com/hydratereminder](https://github.com/jmakhack/hydrate-reminder/tree/master/src/main/java/com/hydratereminder) and follow the existing conventions.
