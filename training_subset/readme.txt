This is a preliminary sample of vehicle photos, similar to the training subset that will be provided for the computer vision project.

How these images were selected:
- We chose %% of our customers, omitting any customers who duplicate each other's photos.
- For each customer, we chose up to three vehicles.
- For each vehicle, we deleted any files outside the "img" folder.
- In each "img" folder, we deleted any files outside the "ec", "i" and "closeups" subfolders.
- In each "img/closeups" subfolder, we deleted any files that reiterate the exterior or interior shots.

Many of the images are 640x480, but not all. For example:
- The "westborotoyota" images are 586x426.
- The "experiencegroup" images are 1600x1068, and include no interior or closeup shots.

Naming conventions:
- A VIN (vehcile identification number) uniquely identifies each vehicle.
- Each folder identifies a customer. (Customer names aren't relevant to the computer vision task, though.)
- Within each customer's folder are up to three sets of photos, each identified by a VIN (e.g. "1b3hb28b77d106769")
- Within each VIN's folder is an "img" folder.
- Within each "img" folder are up to 3 subfolders.
- The "ec" folder contains exterior images, in clockwise order.
- The "i" folder contains interior images, in counterclockwise order.
- The "closeups" folder contains closeup images in an arbitrary order.
