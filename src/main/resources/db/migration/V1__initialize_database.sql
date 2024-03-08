create table metadata (
    id UUID NOT NULL DEFAULT gen_random_uuid(),
    title VARCHAR(255) NOT NULL,
    duration INTEGER NOT NULL,
    media_s3_uuid UUID NOT NULL,
    preview_s3_uuid UUID NOT NULL,
    thumbnail_s3_uuid UUID NOT NULL
)
