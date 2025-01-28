use std::fs;

pub fn read_file(path: &str) -> String
{
    return match fs::read_to_string(path)
    {
        Ok(file_contents) => file_contents,
        Err(e) => panic!("Unable to open file {}, (err: {}", path, e),
    }
}