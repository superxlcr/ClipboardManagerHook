# ClipboardManagerHook
A Hook Test For ClipboardManager  

## Hook Method 1
Hook <font color=#DC143C>sService</font> (A IClipboard cache) in <font color=#DC143C>ClipboardManager</font>  
replace it with our proxy System Service  
Let the clipboardManager always has clipData and always returns "you are hook!"  

## Hook Method 2
Hook <font color=#DC143C>sCache</font> (A HashMap of System Service IBinder) in <font color=#DC143C>ServiceManager</font>  
Let the proxy IBinder return our proxy System Service in Method <font color=#DC143C>queryLocalInterface</font>  
Let the clipboardManager always has clipData and always returns "you are hook!"  
