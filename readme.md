# photography app

### todo

* resize images - done
    * maybe steps should be sync to location, then downsize
    * resolution of 800 ish should be ok
    * https://stackoverflow.com/questions/12879540/image-resizing-in-java-to-reduce-image-size
    
* resizing now works but:
    * creates duplicates - we need to only resize 'non-resized' images - fixed
    * resolution is too low, or the resizer is too low quality, maybe increase or add Method.ULTRA_QUALITY - fixed
    
* add favicon
* change title of index page to just zckd - done
* add pagination or infinite scroll
    * functionality - done
    * buttons - done
        * remove button on first and last page - done
    
* fix needed for vertical photos - done
* new photos aren't loading properly - think it's to do with the path
    * try putting it in ~/images or something - done
    
* try adding continious integration?