use std::fs;

pub fn update_file(path: &str, data: &String) -> bool
{
    return match fs::write(path, data)
    {
        Ok(_) => true,
        Err(_) => false,
    }
}