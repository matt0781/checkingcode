import os

def merge_java_files_in_directory(directory, base_package):
    """
    Recursively merge Java files from the given directory and subdirectories,
    considering the Java package structure.
    """
    merged_content = ""
    for root, dirs, files in os.walk(directory):
        package = root.replace(directory, '').replace(os.sep, '.')
        if package.startswith('.'):
            package = package[1:]
        full_package = f"{base_package}{package}"
        for file in files:
            if file.endswith(".java"):
                file_path = os.path.join(root, file)
                with open(file_path, 'r') as f:
                    file_content = f.read()
                    file_header = f"\n\n/********** Package: {full_package}, File: {file} **********/\n\n"
                    merged_content += file_header + file_content
    return merged_content

# Get the current directory as the base directory for Java packages
base_directory = os.getcwd()

# Define the base package name (modify this as needed)
base_package = "com.yourdomain."

# Merge Java files
merged_content = merge_java_files_in_directory(base_directory, base_package)

# Write the merged content to a new file
with open("merged_java_code.txt", 'w') as merged_file:
    merged_file.write(merged_content)

print("Merged Java code has been saved to merged_java_code.txt")
