#!/bin/bash

# Set the repository URL and branch
REPO_URL="git@github.com:milandangol/adex.git"
BRANCH="manifest"  # Set the branch name as a constant

# Check if the correct number of arguments is provided
if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <relative_path_to_deployment_yaml>"
    exit 1
fi

RELATIVE_PATH="$1"
BUILD_NUMBER="$BUILD_NUMBER_ENV"  # Assuming BUILD_NUMBER_ENV is the environment variable containing the build number

# Check if the environment variable is set
if [ -z "$BUILD_NUMBER" ]; then
    echo "Error: The environment variable BUILD_NUMBER is not set."
    exit 1
fi

# Create a temporary directory
TEMP_DIR=$(mktemp -d)

# Clone the repository to the temporary directory and switch to the specified branch
git clone --branch "$BRANCH" "$REPO_URL" "$TEMP_DIR" || { echo "Failed to clone repository"; exit 1; }
cd "$TEMP_DIR" || { echo "Failed to enter repository directory"; exit 1; }

# Set the file path
MANIFEST="$RELATIVE_PATH"

# Check if the file exists
if [ ! -f "$MANIFEST" ]; then
    echo "Error: The specified file $MANIFEST does not exist."
    exit 1
fi

# Extract the current image from the manifest file
OLD_IMAGE=$(grep -E '^\s*image:' "$MANIFEST" | awk '{print $2}')

# Set the new image with the updated build number
NEW_IMAGE="harbor.adexassesment.com/adex-assesment-dev/nodejs-frontend:${BUILD_NUMBER}"

# Replace the image in the specified manifest file
sed -i "s|$OLD_IMAGE|$NEW_IMAGE|g" "$MANIFEST" || { echo "Failed to replace image in $MANIFEST"; exit 1; }

# Display the updated deployment.yaml file

# Commit and push the changes back to the repository
git add "$MANIFEST" || { echo "Failed to stage changes"; exit 1; }
git commit -m "Update image to $NEW_IMAGE" || { echo "Failed to commit changes"; exit 1; }
git push origin "$BRANCH" || { echo "Failed to push changes"; exit 1; }

# Move back to the original directory
cd -

# Remove the temporary directory
rm -rf "$TEMP_DIR"

echo "Image in $MANIFEST replaced successfully with $NEW_IMAGE and changes pushed to the repository"