# Dialogs  

A dialog is a small window that prompts the user to make a decision or enter additional information. It used for modal events that require users to take an action before they can proceed.


- **AlertDialog**  
A dialog that can show a title, up to three buttons, a list of selectable items, or a custom layout.
- **DatePickerDialog/TimePickerDialog**  
A dialog with a pre-defined UI that allows the user to select a date or time. 



**Creating a Dialog Fragment**  
You can accomplish a wide variety of dialog designs—including custom layouts and those described in the Dialogs design guide—by extending DialogFragment and creating a AlertDialog in the `onCreateDialog()` callback method.  
For example, here's a basic AlertDialog that's managed within a DialogFragment:  
```java
public class FireMissilesDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_fire_missiles)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
```
