# ClipboardManagerHook
A Hook Test For ClipboardManager  

## Hook Method 1
Hook **sService** (A IClipboard cache) in **ClipboardManager**  
replace it with our proxy System Service  
Let the clipboardManager always has clipData and always returns "you are hook!"  

## Hook Method 2
Hook **sCache** (A HashMap of System Service IBinder) in **ServiceManager**  
Let the proxy IBinder return our proxy System Service in Method **queryLocalInterface**  
Let the clipboardManager always has clipData and always returns "you are hook!"  
