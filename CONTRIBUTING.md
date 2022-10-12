# How to Contribute

Hey, it's nice to see fellow software developers interested in contributing to a project that aims to assist OSRS players in living a healthy lifestyle. Come join the [Discord](https://discord.gg/RTgxfFW9mS) to meet fellow contributors, to ask any questions, or to just say hi!

Before contributing to a bugfix or feature, please search [Issues](https://github.com/jmakhack/hydrate-reminder/issues) to see if the request already exists and/or is currently being worked on. If not, create a [New Issue](https://github.com/jmakhack/hydrate-reminder/issues/new/choose) describing the enhancement or bugfix for the plugin. No pull requests should be created without an associated issue. If you plan to work on an issue, just leave a comment on the issue asking to pick it up and then you're free to work on it (unless someone previously commented the same).

When making any code changes, please make sure to run the build using [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).

Make sure to ensure that no newly added functionality goes against [Jagex's rules](https://secure.runescape.com/m=news/another-message-about-unofficial-clients?oldschool=1).  
If adding any third party dependencies, follow the guide found at [plugin-hub](https://github.com/runelite/plugin-hub#third-party-dependencies).

## Setting Up and Building the Project

The following steps go through how to setup the development environment and how to build and run the project. The [IntelliJ IDEA](https://www.jetbrains.com/idea/) development environment is recommended for use on this project. The community edition is free to download and use. For those who plan to use a different IDE, you may have to put in more work to figure out how to properly setup and build the project.

1.  Open a web browser to https://runelite.net/ and download the latest RuneLite client for your operating system. Try running the client and make sure that it runs properly on your device.

2.  Clone the GitHub project onto your computer by opening IntelliJ IDEA, navigating to `File > New > Project from Version Control...`, and inputting `https://github.com/jmakhack/hydrate-reminder` into the URL field. The directory field can be any directory you would like to save the project to. When done, click `Clone`.

![image](https://user-images.githubusercontent.com/1442227/185886676-a5c03998-97b1-458f-87ba-c855c2fa0a9f.png)
    
3.  Navigate to `Run > Edit Configurations...` and add `-ea` to VM options. Afterwards, click `OK`. (Step 5 of the plugin-hub [README](https://github.com/runelite/plugin-hub/blob/master/README.md#using-the-template-repository) has more info on this if there are any issues with this step)

4.  Open a web browser to https://runelite.net/ and note the `Latest release` version on the homepage. In IntelliJ IDEA, open the `build.gradle` file and change the `runeLiteVersion` to match that of the `Latest release` version from the website. After the change, there should be a small elephant icon that appears on the upper right. Click on it to ensure that the new gradle changes have been properly loaded.

5.  In IntelliJ IDEA, open up `src/test/java/com/hydratereminder/HydrateReminderTest.java` and run the class by clicking on the green play icon next to `public class HydrateReminderTest`. Another way to run the project is by right clicking the file in the project navigator and selecting `Run 'HydrateReminderTest'`. At this point, the RuneLite client should open and the plugin with the latest updates should be running there.

If there are any issues setting up the development environment or running the project, feel free to ping @jmakhack in the comments of any issue for further help and debugging. Some additional helpful resources include the official [RuneLite wiki](https://github.com/runelite/runelite/wiki/Building-with-IntelliJ-IDEA) and the official [RuneLite Discord](https://discord.gg/ArdAhnN).

## Testing

The hydrate-reminder project contains jUnit tests located in [src/test/java/com/hydratereminder](https://github.com/jmakhack/hydrate-reminder/tree/master/src/test/java/com/hydratereminder). Please ensure that all the unit tests are passing before submitting a change. If the unit tests are failing as a result of a code change, identify why the test is failing and either fix the newly written code or update the test if it is no longer valid. Adding new unit tests for new features/enhancements is strongly encouraged but not strictly required (a separate testing task will be opened if not needed).

When a new pull request is opened, the unit tests are run automatically as part of the pre-merge pipeline for every update to ensure that they are all passing before merging in the code. New code changes are not allowed to be merged into the master branch until all tests are passing. The unit tests are also run on a nightly basis to ensure the project's stable condition. The current build status can be viewed on the [README](https://github.com/jmakhack/hydrate-reminder/blob/master/README.md) as a badge. Manual testing is also done as a final step before every release as a sanity check of the plugin's quality.

## Submitting Changes

Please send a [GitHub Pull Request](https://github.com/jmakhack/hydrate-reminder/pull/new/master) with clear details on what was done (read more about [pull requests](http://help.github.com/pull-requests/)). All pull requests should be targeted towards the master branch.

Always write a clear and concise commit message for your commits. One-line messages are fine for small changes, but bigger changes should look like the following:

    $ git commit -m "brief commit summary
    > 
    > A paragraph describing what changed and its impact."

## Coding Conventions

When adding new classes, methods, and fields, please add the appropriate JavaDocs to keep the codebase in an easily maintainable state.

[Codacy](https://app.codacy.com/gh/jmakhack/hydrate-reminder/dashboard) is used to measure the overall code quality of hydrate-reminder. The goal is to keep the code quality score at an A level, never allowing it to dip below B.

Otherwise, there are no formal, documented coding conventions for this project to follow, but please take a look at the existing code under [src/main/java/com/hydratereminder](https://github.com/jmakhack/hydrate-reminder/tree/master/src/main/java/com/hydratereminder) and follow the existing conventions.

## Frequently Asked Questions

### Why is it that when I try to run the project, the RuneLite client loads successfully but the plugin is nowhere to be seen?

This issue commonly occurs whenever there is a certain mismatch in RuneLite versions. Navigate to https://runelite.net/ and note the latest release version. Afterwards, open `build.gradle` and note the value for `runeLiteVersion`. When these two values do not match, the plugin will not appear when running the project. Update the value of `runeLiteVersion` to match the latest release version. After building and running the project again, the plugin should appear in the RuneLite client.

### Why are there two instances of the hydrate reminder plugin visible in the RuneLite client sidebar when I run the project?

This usually indicates that you already have the hydrate reminder plugin installed on the RuneLite client from the PluginHub. This is usually not an issue however. If you do want to remove the duplicate plugin, open RuneLite independently and uninstalled the hydrate reminder plugin that was installed from the PluginHub. Afterwards, run the project again and there should only be one instance of the plugin visible.

### Where can I get additional help on any issues and/or development questions?

Besides just leaving a comment on any GitHub issues or pull requests, the [Discord](https://discord.gg/RTgxfFW9mS) server is an excellent place where you can ask any questions you may have. Even if there are no questions to ask, it is still a great community to join and say hi!

### Does the project maintainer bite?

~~maybe~~ I would never do such a thing (｡•̀ᴗ-)✧
