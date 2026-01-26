import sys
import cv2
import numpy as np

def process_image(input_path, output_path):
    try:
        # Read the image
        img = cv2.imread(input_path)
        if img is None:
            raise ValueError("Could not read image")

        # Convert to grayscale
        gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
        
        # Apply Canny Edge Detection
        edges = cv2.Canny(gray, 100, 200)
        
        # Invert colors (Black on White looks better for sketches)
        edges_inverted = cv2.bitwise_not(edges)

        # Save the result
        cv2.imwrite(output_path, edges_inverted)
        print("Success")
    except Exception as e:
        print(f"Error: {str(e)}", file=sys.stderr)
        sys.exit(1)

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Usage: python process_image.py <input_path> <output_path>")
        sys.exit(1)
        
    input_file = sys.argv[1]
    output_file = sys.argv[2]
    
    process_image(input_file, output_file)
