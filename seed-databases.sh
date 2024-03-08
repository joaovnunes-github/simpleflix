echo "Killing DB and AWS processes"
docker kill db &
docker kill aws &
wait

echo "Bringing up DB and AWS"
docker-compose -f database/docker-compose.yml up -d &
docker-compose -f localstack/docker-compose.yml up -d &
wait

echo "Cleaning and migrating the database"
mvn flyway:clean -Dflyway.cleanDisabled=false
mvn flyway:migrate

echo "Creating S3 bucket"
awslocal s3api create-bucket --bucket simpleflix

echo "Putting objects into S3 bucket"
awslocal s3api put-object --bucket simpleflix --key media/17014dc3-e8bf-49cb-bea6-beca5d0e89bb --body localstack/media/Luffy_Punches_a_Celestial_Dragon_One_Piece.mp4 &
awslocal s3api put-object --bucket simpleflix --key preview/media/c20a2693-42a9-41bf-ba26-24da10882731 --body localstack/media/Luffy_Punches_a_Celestial_Dragon_One_Piece-preview.mp4 &
awslocal s3api put-object --bucket simpleflix --key preview/image/0c57b82b-649a-4546-a499-4205facaff3b --body localstack/media/Luffy_Punches_a_Celestial_Dragon_One_Piece.png &
wait

echo "Database and Localstack seeded and started"
