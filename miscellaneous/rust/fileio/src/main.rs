mod create;
mod read;
mod update;
mod delete;

use std::process::exit;

use create::create_file;
use delete::delete_file;
use read::read_file;
use update::update_file;

fn main()
{
    let in_file_path: &str = "poem.txt";
    let out_file_path: &str = "modified.txt";
    let empty_file_path: &str = "empty.txt";
 
    // Create file
    println!("CREATING {}", empty_file_path);
    if create_file(empty_file_path) { println!("Successfully created {}", empty_file_path); } else { println!("Could not create {}", empty_file_path); exit(1); }
    println!("\n");

    // Read file
    println!("READING {}\n", in_file_path);
    let file_contents = read_file(in_file_path);

    println!("Inputted file contents: \n{}\n", file_contents);
    // modify file contents
    let split_output = file_contents.split("\n");
    let mut updated_output = String::from("");
    for line in  split_output
    {
        updated_output = format!("{} | {}", updated_output, line);
    }
    println!("Modified data:\n{}\n\n", updated_output);

    // Update/write out modified contents
    println!("UPDATING {}\n", out_file_path);
    update_file(&out_file_path, &updated_output);
    println!("Updated file contents: \n{}\n\n", read_file(out_file_path));

    // Delete file
    println!("DELETING {}", empty_file_path);
    if delete_file(empty_file_path) { println!("Successfully deleted {}", empty_file_path) } else { println!("Could not delete {}", empty_file_path); exit(1); }
}