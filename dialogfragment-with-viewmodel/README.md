# Smart dialogs with DialogFragment and ViewModel

In many situations you might need to show a dialog that has some behavior attached to it, for example loading the price of a premium upgrade from a remote server.

Instead of using AlertDialog, we use DialogFragment, which is an actual fragment with the complete fragment lifecycle.
We also use a view model to separate the view (dialog) from the behavior (loading something from a remote server).

See tutorial [here](https://codedogg.wordpress.com/2020/12/02/smart-dialogs-with-dialogfragment-and-viewmodel/).
