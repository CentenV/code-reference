use std::fs::File;

pub fn create_file(path: &str) -> bool
{
    return match File::create(path)
    {
        Ok(_) => true,
        Err(_ ) => false,
    }
}