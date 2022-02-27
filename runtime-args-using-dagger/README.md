# Inject "runtime" arguments into viewmodel using dagger2's assisted injection

Dagger is dependency injection library that can be used in android. This sample answers the following very common questions:

* How can we use dagger when some arguments are only known at runtime? One example is the ID of a movie displayed in a `MovieActivity`. The ID is not known by dagger as it is *passed* to the activity from outside (i.e. the calling activity).
* How can we inject this runtime arguments into our view model, considering that we have to use android's `ViewModelProvider()`, so that the view model is persisted on configuration changes?

See tutorial [here](https://codedogg.wordpress.com/2022/02/27/inject-runtime-arguments-into-viewmodel-using-dagger2s-assisted-injection/).
