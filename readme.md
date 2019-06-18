# photography app

### todo

* resize images
    * maybe steps should be sync to location, then downsize
    * resolution of 800 ish should be ok
    * https://stackoverflow.com/questions/12879540/image-resizing-in-java-to-reduce-image-size
    
* resizing now works but:
    * creates duplicates - we need to only resize 'non-resized' images
    * resolution is too low, or the resizer is too low quality, maybe increase or add Method.ULTRA_QUALITY