# Android Architecture Components Experiment

## Goal

- Explore Android Architecture Components (ViewModel, LiveData)
- Explore dependency injection with these components

## Requirements

- A simple app that downloads a JSON file to get a list of ping "targets".
- It then concurrently checks the average of 5 pings for each target.
- The results of each test are reported, as well as displaying an icon (also asynchronously).
- Ability to sort the results by time and name.
- Ability to retest a single item.
- Handle errors sympathetically.
- HAndle device connectivity consequences.

## Assumptions

- Support Android KitKat (API 19+).
- The actual ping process assumes the `ping` executable is installed and accessible on the device. In a brief search I couldn't find proper ICMP support for Android and the `InetAddress.getByName(domain).isReachable` API seemed incredibly inconsistent across SDK versions. (It also states that if ICMP fails it will try a TCP connection, so not exactly a ping at all then...)
- No persistence.
- Didn't attempt to support IPv6.
- Didn't add any code to ensure TLS 1.2 was enabled for KitKat (19) devices. (Easy enough to use ProviderInstall.)
- The layout is basic - simple linear layouts. Don't judge me on this - the UI is just to show the functionality. :)
- Could have used the Data Binding Library but as I left the layout very simple didn't feel there was a lot to gain from it.
- The use of `notifyDataSetChanged` each time a target result was set wouldn't be performant for very large target feeds, but then one could always use DiffUtil for incremental RecyclerView updates (for example).

## Architecture

MVVM

## Testing

Due to the short time scale there's just a silly unit test and instrumentation test to illustrate how easily the app, as written, could be tested.

Test coverage:

- model.* - I'd aim for comprehensive unit test coverage
- rest of app - Combination of mocked and instrumented tests

(Further coverage is left for interested readers!)

## Dependencies

- GSON - for converting the JSON into a list of POJOs (`Target` class)
- Glide - for handling the icon images
- Dagger 2 - for dependency injection
- Android Support Library
- JUnit
- Espresso
