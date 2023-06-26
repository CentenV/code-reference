import sys
from getopt import GetoptError, getopt
import hashlib

def md5(file_path):
    expected_hash = input("Desired hash: ")

    hash = hashlib.md5()
    with open(file_path, 'rb') as file:
        data = file.read()
        hash.update(data)
        checksum = hash.hexdigest()
        print(type(checksum))

print_help = lambda : print("Usage: hashing [option] [filename]\n\t-h, --help\tDisplay this page\n\t--md5\tMD5 hash algorithm\n\t--sha256\tSHA-256 algorithm")


if __name__ == "__main__":
    try:
        user_args, user_vals = getopt(sys.argv[1:], "h", ["help", "md5", "sha256"])
        if len(sys.argv) == 1:
            print("Invalid arguments", end="\n")
            print_help()
            exit()
        
        for arg, val in user_args:
            if arg in ("-h", "--help"):
                print_help()
            elif arg in ("--md5"):
                if len(val) == 0:
                    print("Empty parameters", end="\n")
                    print_help()
                    exit()
                else:
                    md5(val)
    
    except GetoptError as e:
        print(e)
        print_help()