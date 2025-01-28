use std::fs;

pub fn delete_file(path: &str) -> bool
{
    return match fs::remove_file(path)
    {
        Ok(_) => true,
        Err(_) => false,
    }
}